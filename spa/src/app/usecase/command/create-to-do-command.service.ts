import {Injectable} from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class CreateToDoCommandService {
  run(input: Input) {
    console.log(input)
  }
}

type Input = {
  title: string,
  body: string,
  category: number,
}
