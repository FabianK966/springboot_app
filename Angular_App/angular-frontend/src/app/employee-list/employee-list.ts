import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { Employee } from '../employee';

@Component({
  selector: 'app-employee-list',
  imports: [RouterOutlet, EmployeeListComponent],
  templateUrl: './employee-list.html',
  styleUrl: './employee-list.css',
})
export class EmployeeListComponent {
  employees: Employee[] | undefined;
  ngOnInit() {
    this.employees = [{
      "id": 1,
      "firstName": "John",
      "lastName": "Doe",
      "emailId": "john.doe@example.com"
    },
    {
      "id": 2,
      "firstName": "Jane",
      "lastName": "Smith",
      "emailId": "jane.smith@example.com"
    }];
}
}
export { EmployeeListComponent as EmployeeList };

