import { Message } from './message';

export class Discussion {
    id: string;
    user1: string;
    user2: string;
    messages: Message[];

    constructor(id: string, user1: string, user2: string) {
        this.id = id;
        this.user1 = user1;
        this.user2 = user2;
        this.messages = [];
      }
  }