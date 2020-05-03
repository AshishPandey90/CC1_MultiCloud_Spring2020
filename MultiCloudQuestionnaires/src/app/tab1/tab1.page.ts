import { Component } from '@angular/core';
import { FormBuilder, Validators, FormGroup } from '@angular/forms';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-tab1',
  templateUrl: 'tab1.page.html',
  styleUrls: ['tab1.page.scss']
})
export class Tab1Page {

  public cloudPreferencesForm: FormGroup;
  private form_vals;

  constructor(private fb: FormBuilder, private http: HttpClient) {

    this.cloudPreferencesForm = this.fb.group({
      expertise: ['', Validators.required],
      performance: ['', Validators.required],
      agility: ['', Validators.required],
      cost: ['', Validators.required],
      security: ['', Validators.required]
    });

  }

  // called when submit button is clicked
  submitSurvey() {
    //console.log("REQUESTING DATA");
    //this.http.get("https://dog.ceo/api/breeds/image/random").subscribe(response => console.log(response));
 

    console.log("POSTING DATA");
    this.form_vals = this.cloudPreferencesForm.value;

    this.http.post("https://google.com", {user_id: "kpsgf7", 
                                          user_session: "kspgf7_4212020214PM",
                                          scale: "5",
                                          scale_identifiers:["Very_Poor","Below_Average","Average","Above_Average","Excellent"],
                                          csp_identifiers:["Geni","AWS","MU"],
                                          user_expertise: this.form_vals.expertise,
                                          criteria:{
                                            performance:this.form_vals.performance,
                                            agility:this.form_vals.agility,
                                            cost: this.form_vals.cost,
                                            security: this.form_vals.security
                                          },
                                          rules:[]
                                          }).subscribe(response => console.log(response))
  }

}
