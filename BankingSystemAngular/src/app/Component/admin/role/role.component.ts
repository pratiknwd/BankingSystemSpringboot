import { Component, OnInit } from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { RoleService } from 'src/app/service/admin/role.service';
import { Role } from 'src/app/Model/admin/role.model';

@Component({
  selector: 'app-role',
  templateUrl: './role.component.html',
  styleUrls: ['./role.component.css']
})
export class RoleComponent implements OnInit {
  roles: Role[] = [];
  newRoleTitle: string = '';
  selectedRole: Role | null = null;
  isEditMode: boolean = false;

  constructor(private roleService: RoleService, private modalService: NgbModal) {}

  ngOnInit(): void {
    this.getAllRoles();
  }

  getAllRoles() {
    this.roleService.getAllRoles().subscribe(
      (roles) => {
        this.roles = roles;
      },
      (error) => console.error('Failed to fetch roles:', error)
    );
  }

  openModal(content: any, role?: Role) {
    if (role) {
      this.isEditMode = true;
      this.selectedRole = role;
      this.newRoleTitle = role.roleTitle;
    } else {
      this.isEditMode = false;
      this.newRoleTitle = '';
    }
    this.modalService.open(content, { centered: true });
  }

  addRole() {
    const newRole: Role = {
      roleId: 0, 
      roleTitle: this.newRoleTitle
    };

    this.roleService.addRole(newRole).subscribe(
      () => {
        alert('Role added successfully!');
        this.getAllRoles();  
        this.modalService.dismissAll();
      },
      (error) => {
        console.error('Failed to add role:', error);
        alert(`Error: ${error.message}`);
      }
    );
  }

  updateRole() {
    if (this.selectedRole) {
      this.roleService.updateRole(this.selectedRole.roleId, { roleTitle: this.newRoleTitle }).subscribe(
        () => {
          alert('Role updated successfully!');
          this.getAllRoles();  
          this.modalService.dismissAll();
        },
        (error) => {
          console.error('Failed to update role:', error);
          alert(`Error: ${error.message}`);
        }
      );
    }
  }

  deleteRole(roleId: number) {
    if (confirm('Are you sure you want to delete this role?')) {
      this.roleService.deleteRole(roleId).subscribe(
        () => {
          alert('Role deleted successfully!');
          this.getAllRoles();  
        },
        (error) => {
          console.error('Failed to delete role:', error);
          alert(`Error: ${error.message}`);
        }
      );
    }
  }
}
