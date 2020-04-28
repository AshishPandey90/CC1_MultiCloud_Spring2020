import { Component } from '@angular/core';
import { FormBuilder, Validators, FormGroup } from '@angular/forms';

@Component({
  selector: 'app-tab2',
  templateUrl: 'tab2.page.html',
  styleUrls: ['tab2.page.scss']
})
export class Tab2Page {

  public customerSatisfactionForm: FormGroup;

  constructor(private fb: FormBuilder) {

    this.customerSatisfactionForm = this.fb.group({
      satisfied: ['', Validators.required],
      recommendations: ['', Validators.required],
      preferences: ['', Validators.required],
      feedback: ['', Validators.compose([Validators.maxLength(200), Validators.pattern("^[a-zA-Z0-9 -.']*$")])]
    });

  }

  submitSurvey() {

  }
}
