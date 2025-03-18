import { WebPlugin } from '@capacitor/core';

import type { LocationStatePlugin } from './definitions';
import { State } from "./definitions";

export class LocationStateWeb extends WebPlugin implements LocationStatePlugin {
  checkPermission(): Promise<State> {
    console.log('checkLocationPermission');
    return Promise.resolve({ status: 'authorizedWhenInUse' });
  }

  openLocationSettings(): Promise<any> {
    return Promise.resolve(undefined);
  }
}
