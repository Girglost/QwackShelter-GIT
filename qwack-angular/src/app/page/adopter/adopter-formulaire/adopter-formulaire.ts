import { Component, OnInit, Inject } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { Animal } from '../../../model/animal';
import { AnimalService } from '../../../service/animal-service';

@Component({
  selector: 'app-adoption-form',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './adopter-formulaire.html',
  styleUrls: ['./adopter-formulaire.css']
})
export class AdoptionForm implements OnInit {

  adoptionForm: FormGroup;

  constructor(private fb: FormBuilder, private animalService: AnimalService){
    this.adoptionForm = this.fb.group({

      nom:['', Validators.required],

      prenom:['', Validators.required],

      email:['', [Validators.required, Validators.email]],

      telephone:[''],

      adresse:['', Validators.required],

      animalId: ['', Validators.required],

      logement:['', Validators.required],

      jardin:['Oui', Validators.required],

      motivation:['', Validators.required]

    });
  }

  onSubmit(){

    if(this.adoptionForm.valid){

      console.log(this.adoptionForm.value);

      alert("Votre demande d'adoption a bien été envoyée !");

      this.adoptionForm.reset();

    }

  }

  animaux: Animal[] = [];

  ngOnInit() {

    this.animalService['getAnimals']().subscribe({

        next: (data: Animal[]) => this.animaux = data,

        error: (err: any) => {
          console.error("Erreur récupération animaux disponibles", err);
        }

    });

}

}
