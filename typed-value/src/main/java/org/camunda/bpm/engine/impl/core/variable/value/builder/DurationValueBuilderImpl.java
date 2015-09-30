/* Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.camunda.bpm.engine.impl.core.variable.value.builder;

import org.camunda.bpm.engine.impl.core.variable.value.DurationValueImpl;
import org.camunda.bpm.engine.variable.value.DurationValue;
import org.camunda.bpm.engine.variable.value.DurationValueUnit;
import org.camunda.bpm.engine.variable.value.builder.DurationValueBuilder;
import org.camunda.commons.utils.EnsureUtil;

/**
 * @author Philipp Ossler
 * @since 7.4
 */
public class DurationValueBuilderImpl implements DurationValueBuilder {

  protected long duration;
  protected DurationValueUnit unit;

  public DurationValueBuilderImpl(long duration) {
    this.duration = duration;
  }

  @Override
  public DurationValue create() {
    EnsureUtil.ensureNotNull("unit", unit);

    return new DurationValueImpl(duration, unit);
  }

  @Override
  public DurationValueBuilder unit(DurationValueUnit unit) {
    this.unit = unit;

    return this;
  }

}
