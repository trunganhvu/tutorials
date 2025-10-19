export interface IProduct {
  id?: number;
  name?: string;
  description?: string | null;
  price?: number;
  stock?: number;
}

export class Product implements IProduct {
  constructor(
    public id?: number,
    public name?: string,
    public description?: string | null,
    public price?: number,
    public stock?: number,
  ) {}
}
