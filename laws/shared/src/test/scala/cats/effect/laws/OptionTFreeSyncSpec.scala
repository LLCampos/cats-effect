/*
 * Copyright 2020 Typelevel
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package cats.effect
package laws

import cats.{Eq, Eval}
import cats.data.OptionT
import cats.effect.kernel.testkit.{freeEval, FreeSyncEq, FreeSyncGenerators, SyncTypeGenerators}
import cats.free.FreeT
import cats.laws.discipline.arbitrary._
import freeEval.{syncForFreeT, FreeEitherSync}

import org.specs2.ScalaCheck
import org.specs2.mutable._

import org.typelevel.discipline.specs2.mutable.Discipline

class OptionTFreeSyncSpec
    extends Specification
    with Discipline
    with ScalaCheck
    with BaseSpec
    with FreeSyncEq {

  import FreeSyncGenerators._
  import SyncTypeGenerators._

  implicit val scala_2_12_is_buggy
      : Eq[FreeT[Eval, Either[Throwable, *], Either[Int, Either[Throwable, Int]]]] =
    eqFreeSync[Either[Throwable, *], Either[Int, Either[Throwable, Int]]]

  checkAll("OptionT[FreeEitherSync]", SyncTests[OptionT[FreeEitherSync, *]].sync[Int, Int, Int])
}