import { Component } from '@angular/core';
import { FormBuilder, Validators, FormGroup } from '@angular/forms';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-tab2',
  templateUrl: 'tab2.page.html',
  styleUrls: ['tab2.page.scss']
})
export class Tab2Page {

  public customerSatisfactionForm: FormGroup;

  constructor(private fb: FormBuilder,private http: HttpClient) {

    this.customerSatisfactionForm = this.fb.group({
      satisfied: ['', Validators.required],
      recommendations: ['', Validators.required],
      preferences: ['', Validators.required],
      feedback: ['', Validators.compose([Validators.maxLength(200), Validators.pattern("^[a-zA-Z0-9 -.']*$")])]
    });

  }

  submitSurvey() {
  console.log("SUBMITTING");
  this.http.post("http://130.127.215.155:8080/cc1/rest/dataCollection", {user_id: "kpsgf7", 
                                          user_session: "kspgf7_4212020214PM",
                                          scale: "5",
                                          scale_identifiers:["Very_Poor","Below_Average","Average","Above_Average","Excellent"],
                                          reccomendation_process:"Great",
                                          reccomendation_template:"Great",
                                          user_suggestion:"random text"}).subscribe(response => console.log(response))

  }
}
