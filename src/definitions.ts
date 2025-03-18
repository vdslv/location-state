export interface LocationStatePlugin {
  checkPermission(): Promise<State>;
  openLocationSettings(): Promise<void>;
}

export interface State {
  status: 'notDetermined' | 'denied' | 'authorizedWhenInUse' | 'authorizedAlways'
}