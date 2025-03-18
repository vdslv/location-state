import Foundation
import Capacitor
import CoreLocation

/**
 * Please read the Capacitor iOS Plugin Development Guide
 * here: https://capacitorjs.com/docs/plugins/ios
 */
@objc(LocationStatePlugin)
public class LocationStatePlugin: CAPPlugin, CAPBridgedPlugin {
    public let identifier = "LocationStatePlugin"
    public let jsName = "LocationState"
    public let pluginMethods: [CAPPluginMethod] = [
        CAPPluginMethod(name: "checkPermission", returnType: CAPPluginReturnPromise),
        CAPPluginMethod(name: "openLocationSettings", returnType: CAPPluginReturnPromise)
    ]

    private let locationManager = CLLocationManager()

    @objc func checkPermission(_ call: CAPPluginCall) {
        var permissionStatus: String = ""

        switch CLLocationManager.authorizationStatus() {
        case .authorizedWhenInUse:
            permissionStatus = "authorizedWhenInUse"
        case .authorizedAlways:
            permissionStatus = "authorizedAlways"
        case .denied, .restricted:
            permissionStatus = "denied"
        default:
            permissionStatus = "notDetermined"
        }

        call.resolve(["status": permissionStatus])
    }

    @objc func openLocationSettings(_ call: CAPPluginCall) {
        let status = CLLocationManager.authorizationStatus()

        switch status {
        case .notDetermined:
            requestWhenInUseAuthorization(call)
        case .authorizedWhenInUse:
            requestAlwaysAuthorization(call)
        case .authorizedAlways:
            requestAlwaysAuthorization(call)
            call.resolve(["status": "authorized always"])
        default:
            call.reject("Unknown authorization status")
        }
    }

    private func requestWhenInUseAuthorization(_ call: CAPPluginCall) {
        locationManager.requestWhenInUseAuthorization()
        call.resolve(["status": "requesting when in use"])
    }

    private func requestAlwaysAuthorization(_ call: CAPPluginCall) {
        locationManager.requestAlwaysAuthorization()
        call.resolve(["status": "requesting always"])
    }

    private func openSettings(_ call: CAPPluginCall) {
        let settingsUrl = URL(string: UIApplication.openSettingsURLString)!
        if UIApplication.shared.canOpenURL(settingsUrl) {
            UIApplication.shared.open(settingsUrl, options: [:], completionHandler: nil)
            call.resolve(["status": "opened settings"])
        } else {
            call.reject("Failed to open settings")
        }
    }

}
