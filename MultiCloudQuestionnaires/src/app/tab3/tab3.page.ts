import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';

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
  private get_response;

  constructor(private http: HttpClient) {}


  getResults(){
    // retrieve info from backend
    console.log("REQUESTING DATA");

    // this get request shows what the api is currently returning
    this.http.get("http://130.127.215.155:8080/cc1/rest/dataCollection",{responseType: 'text'}).subscribe(response => console.log(response));

    // These two lines do the actual logic when the response is correct
   // this.http.get("http://130.127.215.155:8080/cc1/rest/dataCollection").subscribe((response:Response) => {this.get_response = response.json();});
    
   // this.doughnutChartData = [this.get_response.MU, this.get_response.GENI, this.get_response.AWS];

   //repopulated with different dummy data
    this.doughnutChartData = [.80, .13, .07];
    this.doughnutChartLabels=["MU", "GENI", "AWS"];

    this.showchart = true;
  }

  exportToOnTimeURB(){

    // might make sense to remove this later
    this.showchart = false;
  }
}
