import { Component } from '@angular/core';

import { Machine } from './machine.model';
import { MachineService } from './machine.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  machine: Machine;

  constructor(private machineService: MachineService) {
    this.clear();
  }

  private clear() {
    this.machine = {
      brand: "",
      model: "",
      fabricationYear: ""
    };
  }

  public save() {
    this.machineService.create(this.machine).subscribe(response => this.clear());
  }
}
