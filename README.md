**Logistics Application**

**Product**

`Transport` represents delivery behavior. `Truck` delivers by road and `Ship` delivers by sea.

The UI products are `Button` and `Checkbox`. Each product has Windows and macOS representations.

**Factory Method**

**Road Logistics**

Use `RoadLogistics` — creates a `Truck` through the `Logistics` factory method.

**Sea Logistics**

Use `SeaLogistics` — creates a `Ship` through the `Logistics` factory method.

`Logistics` has one shared `planDelivery(...)` workflow. The selected logistics class decides which transport is created.

**Abstract Factory**

**Windows UI**

Use `WindowsFactory` — creates a `WindowsButton` and a `WindowsCheckbox`.

**macOS UI**

Use `MacOSFactory` — creates a `MacOSButton` and a `MacOSCheckbox`.

`DeliveryApplication` receives a `GUIFactory` and a `Logistics` object, renders the selected UI family and runs the selected delivery.

**Main**

`Main` accepts a delivery mode (`ROAD` or `SEA`) and a UI platform (`WINDOWS` or `MACOS`) through command-line arguments or console input.

**How to run**

Compile from the `assignment2` folder with JDK 17:

`New-Item -ItemType Directory -Force out`

`javac --release 17 -d out (Get-ChildItem -Path src/main/java -Recurse -Filter *.java)`

Run an example:

`java -cp out app.Main ROAD WINDOWS`

Other supported combinations are `SEA WINDOWS`, `ROAD MACOS` and `SEA MACOS`.

Ramin293
