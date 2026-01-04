import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
// Removed circular import of EmployeeList

@Component({
  selector: 'app-employee-list',
  imports: [RouterOutlet, EmployeeListComponent],
  templateUrl: './employee-list.html',
  styleUrl: './employee-list.css',
})
export class EmployeeListComponent {

}
// Removed circular export of EmployeeList
export { EmployeeListComponent as EmployeeList };

