import { WebPlugin } from '@capacitor/core';

import type { LocationStatePlugin } from './definitions';

export class LocationStateWeb extends WebPlugin implements LocationStatePlugin {
  async echo(options: { value: string }): Promise<{ value: string }> {
    console.log('ECHO', options);
    return options;
  }
}
