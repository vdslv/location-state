# location-state

Get location permission status of the app

## Install

```bash
npm install location-state
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

```typescript
openLocationSettings() => Promise<void>
```

--------------------


### Interfaces


#### State

| Prop         | Type                                                                                    |
| ------------ | --------------------------------------------------------------------------------------- |
| **`status`** | <code>'notDetermined' \| 'denied' \| 'authorizedWhenInUse' \| 'authorizedAlways'</code> |

</docgen-api>
