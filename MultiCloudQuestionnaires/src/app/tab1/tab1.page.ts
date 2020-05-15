import { Component } from '@angular/core';
import { FormBuilder, Validators, FormGroup, FormArray } from '@angular/forms';
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
  public rulesArray:FormArray;

  /*rules = [
    {type: "Performance"},
    {type: "Agility"},
    {type: "Security"},
    {type: "Cost"}
  ];

  choices = [
    {phrase: "Excellent"},
    {phrase: "Above Average"},
    {phrase: "Average"},
    {phrase: "Below Average"},
    {phrase: "Poor"}
  ];*/

  constructor(private fb: FormBuilder, private http: HttpClient) {

    this.cloudPreferencesForm = this.fb.group({
      expertise: ['', Validators.required],
      performance: ['', Validators.required],
      agility: ['', Validators.required],
      cost: ['', Validators.required],
      security: ['', Validators.required],
      //    items: this.formBuilder.array([ this.createItem() ])
      rulesArray: this.fb.array([this.createRule()], [Validators.required])

    });

  }

  // Reference: https://alligator.io/angular/reactive-forms-formarray-dynamic-fields/
  // Reference: https://medium.com/aubergine-solutions/add-push-and-remove-form-fields-dynamically-to-formarray-with-reactive-forms-in-angular-acf61b4a2afe

  createRule(): FormGroup{
    return this.fb.group({
      performance2: ['', Validators.required],
      agility2: ['', Validators.required],
      cost2: ['', Validators.required],
      security2: ['', Validators.required],
      CSP: ['', Validators.required]
    });
  }

  addRule(): void{
    this.rulesArray = this.cloudPreferencesForm.get('rulesArray') as FormArray;
    this.rulesArray.push(this.createRule());
  }

  deleteRule(): void{
    this.rulesArray = this.cloudPreferencesForm.get('rulesArray') as FormArray;
    this.rulesArray.removeAt(this.rulesArray.length-1);
  }

  // called when submit button is clicked
  submitSurvey() {
  
    console.log("POSTING DATA");
    this.form_vals = this.cloudPreferencesForm.value;

    this.http.post("http://130.127.215.155:8080/cc1/rest/dataCollection", {user_id: "kpsgf7", 
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
                                          rules:this.rulesArray
                                          }).subscribe(response => console.log(response))
  }

}
