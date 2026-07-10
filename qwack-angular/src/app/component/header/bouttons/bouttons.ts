import { Component } from '@angular/core';

@Component({
  selector: 'app-bouttons',
  imports: [],
  templateUrl: './bouttons.html',
  styleUrl: './bouttons.css',
})
export class Bouttons {

  public faireDon(){
    const son = new Audio('../../../../../public/assets/son/qwack_jordan.m4a')
    son.play();
    alert("Merci pour le Don de 50 € !")
  }



}
