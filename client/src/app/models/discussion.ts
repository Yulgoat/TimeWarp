import { Message } from './message';

export class Discussion {
    id: number;
    titre: string;
    messages: Message[] = [];

    constructor(id: number, titre: string) {
        this.id = id;
        this.titre = titre;
      }
  }