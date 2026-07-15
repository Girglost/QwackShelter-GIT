import { Animal } from '../model/animal';

const NB_IMAGES_PAR_TYPE: Record<string, number> = {
  Chat: 5,
  Canard: 5,
  Chien: 5,
  Poule: 3,
  NAC: 5,
};

export function getImageAnimal(a: Animal): string {
  const type = a.typeAnimal;
  const nb = NB_IMAGES_PAR_TYPE[type] ?? 1;
  const index = (Number(a.id) % nb) + 1;
  const prefixe = type.toString().toLowerCase();

  return `assets/image/animaux/${type}/${prefixe}${index}.png`;
}
