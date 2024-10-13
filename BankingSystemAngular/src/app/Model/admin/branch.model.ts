export interface AddressDto {
  buildingName: string;
  street: string;
  city: string;
  state: string;
  country: string;
  zipcode: number;
}

export interface Branch {
  ifsc: string;
  addressDto: AddressDto;
}
