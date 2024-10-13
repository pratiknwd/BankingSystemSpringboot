import { Component } from '@angular/core';
import { Application } from 'src/app/Model/application';
import { ApplicationService } from 'src/app/service/application.service';

@Component({
  selector: 'app-view-application',
  templateUrl: './view-application.component.html',
  styleUrls: ['./view-application.component.css'],
})
export class ViewApplicationComponent {
  applications: Application[] = [];

  constructor(private applicationService: ApplicationService) {
    this.loadApplication();
  }

  loadApplication(): void {
    this.applicationService.getCurrentUserApplications().subscribe({
      next: (response) => {
        this.applications = response;
        // console.log(response);
        // console.log(this.applications);
      },
      error: (error) => {
        console.log(error.error.message);
      },
    });
  }
}
