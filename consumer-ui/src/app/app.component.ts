import { Component } from '@angular/core';
import { PoNotificationService, PoPieChartSeries } from '@portinari/portinari-ui';
import * as Stomp from 'stompjs';

import { WebSocketConnector } from './web-socket-connector';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  public machinesCreated: number = 0;

  public series: PoPieChartSeries[] = [];

  private allMachines = [];

  constructor(private poNotification: PoNotificationService) {
    const ws = new WebSocketConnector("http://localhost:8081/socket", "machinesCreated", this.onMessage.bind(this));
  }

  onMessage(message: Stomp.Message): void {
    const machine = JSON.parse(message.body);
    this.allMachines.push(machine);
    this.machinesCreated = this.allMachines.length;
    this.refreshChart();
    this.poNotification.success(`Nova máquina cadastrada. Marca: ${machine["brand"]} | Modelo: ${machine["model"]}`)
  }

  private refreshChart() {
    this.series = this.allMachines.reduce((newArray, currentValue, currentIndex, array) => {
      const index = newArray.findIndex(serie => serie["category"] === currentValue["brand"]);
      if (index >= 0) {
        newArray[index] = { category: currentValue["brand"], value: newArray[index]["value"] + 1 };
      } else {
        newArray.push({ category: currentValue["brand"], value: 1 });
      }
      return newArray;
    }, []);
  }
}
