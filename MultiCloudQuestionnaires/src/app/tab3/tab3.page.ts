import { Component } from '@angular/core';

@Component({
  selector: 'app-tab3',
  templateUrl: 'tab3.page.html',
  styleUrls: ['tab3.page.scss']
})
export class Tab3Page {

  showchart: boolean = false;
  //chartData:number[] = [];

  //chartLabels:String[] = [];
  public doughnutChartLabels:string[] = [];
  public doughnutChartData:number[] = [];
  public doughnutChartType:string = 'doughnut';

  constructor() {}


  getResults(){
    // retrieve info from backend

    // these values will be populated by results
    // dummy values for now
    this.doughnutChartData = [.30, .10, .60];
    this.doughnutChartLabels=["MU", "GENI", "AWS"];

    this.showchart = true;
  }

  exportToOnTimeURB(){

    // might make sense to remove this later
    this.showchart = false;
  }
}
