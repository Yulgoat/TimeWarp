import { Message } from './message';

export class Discussion {
    id: string;
    timestamp: number;
    user1: string;
    user2: string;
    unreadMessage: boolean;

    constructor(id: string, timestamp: number, user1: string, user2: string, unreadMessage: boolean) {
        this.id = id;
        this.timestamp = timestamp;
        this.user1 = user1;
        this.user2 = user2;
        this.unreadMessage = unreadMessage;
      }
  }