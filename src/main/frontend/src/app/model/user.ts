export class User {
  id: string | undefined;
  username: string;
  password: string;
  email: string;
  role: string | undefined;
  createdAt: string | undefined;

  constructor() {
    this.username = '';
    this.password = '';
    this.email = '';
  }
}
