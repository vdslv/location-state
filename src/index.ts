import { registerPlugin } from '@capacitor/core';

import type { LocationStatePlugin } from './definitions';

const LocationState = registerPlugin<LocationStatePlugin>('LocationState', {
  web: () => import('./web').then((m) => new m.LocationStateWeb()),
});

export * from './definitions';
export { LocationState };
