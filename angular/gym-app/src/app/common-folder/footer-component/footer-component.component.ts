import {Component} from '@angular/core';
import {LanguageService} from "../../../service/language.service";

@Component({
  selector: 'app-footer-component',
  templateUrl: './footer-component.component.html',
  styleUrls: ['./footer-component.component.css']
})
export class FooterComponentComponent {
  selectedLanguage: string;
  languages = [
    {value: 'en', viewValue: 'English'},
    {value: 'es', viewValue: 'Spanish'},
  ];

  private languageService: LanguageService;

  constructor(languageService: LanguageService) {
    this.languageService = languageService;
    this.selectedLanguage = this.languageService.getLanguage;
  }

  onLanguageChange() {
    this.languageService.setLanguage = this.selectedLanguage;
  }
}
