# location-state-permissions

Capacitor plugin for location permission request and state.

## Install

```bash
npm install location-state-permissions
npx cap sync
```

## API

<docgen-index>

* [`checkPermission()`](#checkpermission)
* [`openLocationSettings()`](#openlocationsettings)
* [Interfaces](#interfaces)

</docgen-index>

<docgen-api>
<!--Update the source file JSDoc comments and rerun docgen to update the docs below-->

### checkPermission()

```typescript
checkPermission() => Promise<State>
```

**Returns:** <code>Promise&lt;<a href="#state">State</a>&gt;</code>

--------------------


### openLocationSettings()
#### IOS: Request location permission two times to see "Change to Always allow" option.
#### Android: Opens the location settings page (available when permission is set to "while use").
```typescript
openLocationSettings() => Promise<void>
```

--------------------


###Example

```typescript
import { LocationStatePlugin, State } from 'location-state-permissions';

const LocationStatePlugin = registerPlugin<LocationStatePlugin>('LocationState');

LocationStatePlugin.openLocationSettings();
LocationStatePlugin.checkPermission().then((state: State) => {})
```



### Interfaces


#### State

| Prop         | Type                                                                                    |
| ------------ | --------------------------------------------------------------------------------------- |
| **`status`** | <code>'notDetermined' \| 'denied' \| 'authorizedWhenInUse' \| 'authorizedAlways'</code> |

</docgen-api>
