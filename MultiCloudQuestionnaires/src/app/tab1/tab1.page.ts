import { Component } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';

@Component({
  selector: 'app-tab1',
  templateUrl: 'tab1.page.html',
  styleUrls: ['tab1.page.scss']
})
export class Tab1Page {

  constructor(private fb: FormBuilder) {}

  cloudPreferencesForm = this.fb.group({
    expertise: ['', Validators.required],
    performance: ['', Validators.required],
    agility: ['', Validators.required],
    cost: ['', Validators.required],
    security: ['', Validators.required]
  });

  
  // called when submit button is clicked
  submitSurvey(){

  }

}
