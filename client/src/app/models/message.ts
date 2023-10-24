export class Message {
    id: number;
    content: string;
    sentByMe: boolean;
    date: Date;

    constructor(id: number, content: string, sentByMe: boolean, date: Date) {
        this.id = id;
        this.content = content;
        this.sentByMe = sentByMe;
        this.date = date;
      }
  }