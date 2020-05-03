import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-tab3',
  templateUrl: 'tab3.page.html',
  styleUrls: ['tab3.page.scss']
})
export class Tab3Page {

  rules = [
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
  ];

  public ruleCreationForm: FormGroup;


  constructor(private fb: FormBuilder) {

    this.ruleCreationForm = this.fb.group({
      performance: ['', Validators.required],
      agility: ['', Validators.required],
      cost: ['', Validators.required],
      security: ['', Validators.required],
      CSP: ['', Validators.required]
    });

  }

  submitSurvey(){

  }

}
