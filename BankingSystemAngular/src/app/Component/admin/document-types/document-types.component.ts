import { Component } from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { DocumentType } from 'src/app/Model/admin/document-type.model';
import { DocumentTypeService } from 'src/app/service/admin/document-type.service';

@Component({
  selector: 'app-document-types',
  templateUrl: './document-types.component.html',
  styleUrls: ['./document-types.component.css']
})
export class DocumentTypesComponent {
  documentTypes: DocumentType[] = [];
  selectedDocumentType: DocumentType | null = null;
  newDocumentType: string = '';
  isEditMode: boolean = false;

  constructor(private documentTypeService: DocumentTypeService, private modalService: NgbModal) {}

  ngOnInit(): void {
    this.getAllDocumentTypes();
  }

  getAllDocumentTypes() {
    this.documentTypeService.getAllDocumentTypes().subscribe(
      (data) => this.documentTypes = data,
      (error) => console.error('Failed to load document types:', error)
    );
  }

  openModal(content: any, documentType?: DocumentType) {
    if (documentType) {
      this.isEditMode = true;
      this.selectedDocumentType = documentType;
      this.newDocumentType = documentType.documentType;
    } else {
      this.isEditMode = false;
      this.newDocumentType = '';
    }
    this.modalService.open(content, { centered: true });
  }

  addDocumentType() {
    // Create the document type object
    const newDocument: DocumentType = {
      documentTypeId: 0, 
      documentType: this.newDocumentType
    };
  
    
    this.documentTypeService.addDocumentType(newDocument).subscribe(
      () => {
        alert('Document Type added successfully!');
        this.getAllDocumentTypes();  
        this.modalService.dismissAll();
      },
      (error) => console.error('Failed to add document type:', error)
    );
  }
  

  updateDocumentType() {
    if (this.selectedDocumentType) {
      this.documentTypeService.updateDocumentType(this.selectedDocumentType.documentTypeId, this.newDocumentType).subscribe(
        () => {
          alert('Document Type updated successfully!');
          this.getAllDocumentTypes(); 
          this.modalService.dismissAll();
        },
        (error) => console.error('Failed to update document type:', error)
      );
    }
  }

  deleteDocumentType(documentTypeId: number) {
    this.documentTypeService.deleteDocumentType(documentTypeId).subscribe(
      () => {
        alert('Document Type deleted successfully!');
        this.getAllDocumentTypes();
      },
      (error) => console.error('Failed to delete document type:', error)
    );
  }
}
