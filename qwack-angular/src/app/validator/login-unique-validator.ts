import { AbstractControl, AsyncValidatorFn } from '@angular/forms';
import { catchError, map, of } from 'rxjs';
import { PersonneService } from '../service/personne-service';


export function loginUniqueValidator(
    personneService: PersonneService
): AsyncValidatorFn {


    return (loginControl: AbstractControl) => {


        if (!loginControl.value) {
            return of(null);
        }


        return personneService
            .checkLogin(loginControl.value)
            .pipe(

                map(exists => {

                    return exists
                        ? { loginTaken: true }
                        : null;

                }),


                catchError(() => of(null))

            );

    };

}
