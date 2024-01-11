export class UserSettings {
    theme: number;
    language: string;
    unreadBadges: boolean;
    notificationSound: boolean;
    profileImage: string;

    constructor(theme: number, language: string, unreadBadges: boolean, notificationSound: boolean, profileImage: string) {
        this.theme = theme;
        this.language = language;
        this.unreadBadges = unreadBadges;
        this.notificationSound = notificationSound;
        this.profileImage = profileImage;
      }
}
