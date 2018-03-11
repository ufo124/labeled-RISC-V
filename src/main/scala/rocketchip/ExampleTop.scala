// See LICENSE for license details.

package rocketchip

import Chisel._
import cde.{Parameters, Field}
import junctions._
import coreplex._
import rocketchip._

import sifive.blocks.devices.uart.{UARTConfig, PeripheryUART, PeripheryUARTBundle, PeripheryUARTModule, UARTGPIOPort}

/** Example Top with Periphery */
class ExampleTop(q: Parameters) extends BaseTop(q)
    with PeripheryBootROM
    with PeripheryDebug
    with PeripheryExtInterrupts
    with PeripheryCoreplexLocalInterrupter
    with PeripheryMasterMem
    with PeripherySlave {
  override lazy val module = Module(new ExampleTopModule(p, this, new ExampleTopBundle(p)))
}

class ExampleTopBundle(p: Parameters) extends BaseTopBundle(p)
    with PeripheryBootROMBundle
    with PeripheryDebugBundle
    with PeripheryExtInterruptsBundle
    with PeripheryCoreplexLocalInterrupterBundle
    with PeripheryMasterMemBundle
    with PeripherySlaveBundle

class ExampleTopModule[+L <: ExampleTop, +B <: ExampleTopBundle](p: Parameters, l: L, b: => B) extends BaseTopModule(p, l, b)
    with DirectConnection
    with PeripheryBootROMModule
    with PeripheryDebugModule
    with PeripheryExtInterruptsModule
    with PeripheryCoreplexLocalInterrupterModule
    with PeripheryMasterMemModule
    with PeripherySlaveModule
    with HardwiredResetVector

/** Example Top with TestRAM */
class ExampleTopWithTestRAM(q: Parameters) extends ExampleTop(q)
    with PeripheryTestRAM {
  override lazy val module = Module(new ExampleTopWithTestRAMModule(p, this, new ExampleTopWithTestRAMBundle(p)))
}

class ExampleTopWithTestRAMBundle(p: Parameters) extends ExampleTopBundle(p)
    with PeripheryTestRAMBundle

class ExampleTopWithTestRAMModule[+L <: ExampleTopWithTestRAM, +B <: ExampleTopWithTestRAMBundle](p: Parameters, l: L, b: => B) extends ExampleTopModule(p, l, b)
    with PeripheryTestRAMModule
