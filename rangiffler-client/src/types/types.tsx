export type User = {
  id: number,
  username: string,
  firstName: string,
  lastName: string,
  avatar: string,
  friendState: friendState,
}

export type ApiCountry = {
  id: string,
  code: string,
  name: string,
};

export type MapCountry = {
  country: string,
  value: number,
};

export type Photo = {
  id: string,
  username: string,
  src: string,
  countryCode: string,
  description?: string,
}

export type friendState = "FRIEND" | "NOT_FRIEND" | "INVITATION_SENT" | "INVITATION_RECEIVED"
