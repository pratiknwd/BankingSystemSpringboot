import { Component } from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { ApplicationType } from 'src/app/Model/admin/application-type.model';
import { ApplicationTypeService } from 'src/app/service/admin/application-type.service';

@Component({
  selector: 'app-application-type',
  templateUrl: './application-type.component.html',
  styleUrls: ['./application-type.component.css']
})
export class ApplicationTypeComponent {
  applicationTypes: ApplicationType[] = [];
  selectedApplicationType: ApplicationType | null = null;
  newApplicationType: string = '';
  isEditMode: boolean = false;

  constructor(private appTypeService: ApplicationTypeService, private modalService: NgbModal) {}

  ngOnInit(): void {
    this.getAllApplicationTypes();
  }

  getAllApplicationTypes() {
    this.appTypeService.getAllApplicationTypes().subscribe(
      (data) => this.applicationTypes = data,
      (error) => console.error('Failed to load application types:', error)
    );
  }

  openModal(content: any, applicationType?: ApplicationType) {
    if (applicationType) {
      this.isEditMode = true;
      this.selectedApplicationType = applicationType;
      this.newApplicationType = applicationType.applicationType;
    } else {
      this.isEditMode = false;
      this.newApplicationType = '';
    }
    this.modalService.open(content, { centered: true });
  }

  addApplicationType() {
    this.appTypeService.addApplicationType(this.newApplicationType).subscribe(
      () => {
        alert('Application Type added successfully!');
        this.getAllApplicationTypes();  
        this.modalService.dismissAll();
      },
      (error) => console.error('Failed to add application type:', error)
    );
  }

  updateApplicationType() {
    if (this.selectedApplicationType) {
      this.appTypeService.updateApplicationType(this.selectedApplicationType.applicationTypeId, this.newApplicationType).subscribe(
        () => {
          alert('Application Type updated successfully!');
          this.getAllApplicationTypes(); 
          this.modalService.dismissAll();
        },
        (error) => console.error('Failed to update application type:', error)
      );
    }
  }
}
