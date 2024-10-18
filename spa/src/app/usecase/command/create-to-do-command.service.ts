import {inject, Injectable} from '@angular/core';
import {CreateToDoBackendAPIService} from '../../backend/create-to-do-backend-api.service';

@Injectable({
  providedIn: 'root'
})
export class CreateToDoCommandService {
  private readonly api = inject(CreateToDoBackendAPIService)

  run(input: Input) {
    this.api.run(input)
  }
}

type Input = {
  title: string,
  body: string,
  category: number,
}
