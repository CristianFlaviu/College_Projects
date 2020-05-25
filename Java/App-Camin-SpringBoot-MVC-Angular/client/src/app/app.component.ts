import { Component } from '@angular/core';
import { AuthService } from './general/auth.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'material-tutorial';

  private service:AuthService;

  getRole()
  {
    return this.service.getRole();
  }
}
