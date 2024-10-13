import { isPlatformBrowser } from '@angular/common';
import { Inject, Injectable, PLATFORM_ID } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class StorageService {
  constructor(@Inject(PLATFORM_ID) private platformId: Object) {}

  private getStorage(): Storage | null {
    if (isPlatformBrowser(this.platformId)) {
      return window.localStorage;
    }
    return null;
  }

  setItem(key: string, value: string): void {
    const storage = this.getStorage();
    if (storage) {
      storage.setItem(key, value);
    }
  }

  getItem(key: string): string | null {
    const storage = this.getStorage();
    if (storage) {
      return storage.getItem(key);
    }
    return null;
  }

  removeItem(key: string): void {
    const storage = this.getStorage();
    if (storage) {
      storage.removeItem(key);
    }
  }

  clear(): void {
    const storage = this.getStorage();
    if (storage) {
      storage.clear();
    }
  }
}
