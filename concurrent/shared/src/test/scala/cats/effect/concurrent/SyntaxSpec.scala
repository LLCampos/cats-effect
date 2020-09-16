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

package cats.effect.concurrent

import cats.effect.kernel.{Async, Concurrent, GenConcurrent, Sync}
import org.specs2.mutable.Specification

class SyntaxSpec extends Specification {
  "concurrent data structure construction syntax" >> ok

  def async[F[_]: Async] = {
    Ref.of[F, String]("foo")
    Ref[F].of(15)
    Deferred[F, Unit]
    Semaphore[F](15)
    MVar[F].of(1)
    MVar[F].empty[String]
    MVar.empty[F, String]
    MVar.of[F, String]("bar")
  }

  def genConcurrent[F[_]](implicit F: GenConcurrent[F, _]) = {
    Ref.of[F, Int](0)
    Deferred[F, Unit]
  }

  def sync[F[_]](implicit F: Sync[F]) = {
    Ref.of[F, Int](0)
  }

  def preciseConstraints[F[_]: Ref.Make] = {
    Ref.of[F, String]("foo")
    Ref[F].of(15)
  }

  def semaphoreIsDeriveable[F[_]](implicit F: Concurrent[F]) =
    Semaphore[F](11)
}
