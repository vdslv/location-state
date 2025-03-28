// swift-tools-version: 5.9
import PackageDescription

let package = Package(
    name: "LocationStatePermissions",
    platforms: [.iOS(.v14)],
    products: [
        .library(
            name: "LocationStatePermissions",
            targets: ["LocationStatePlugin"])
    ],
    dependencies: [
        .package(url: "https://github.com/ionic-team/capacitor-swift-pm.git", from: "7.0.0")
    ],
    targets: [
        .target(
            name: "LocationStatePlugin",
            dependencies: [
                .product(name: "Capacitor", package: "capacitor-swift-pm"),
                .product(name: "Cordova", package: "capacitor-swift-pm")
            ],
            path: "ios/Sources/LocationStatePlugin"),
        .testTarget(
            name: "LocationStatePluginTests",
            dependencies: ["LocationStatePlugin"],
            path: "ios/Tests/LocationStatePluginTests")
    ]
)