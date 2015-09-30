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
package org.camunda.bpm.engine.test.variables;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.collection.IsMapContaining.hasEntry;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.util.Collections;
import java.util.Map;

import org.camunda.bpm.engine.impl.core.variable.type.DurationValueTypeImpl;
import org.camunda.bpm.engine.variable.Variables;
import org.camunda.bpm.engine.variable.type.DurationValueType;
import org.camunda.bpm.engine.variable.value.DurationValue;
import org.camunda.bpm.engine.variable.value.DurationValueUnit;
import org.junit.Test;

/**
 * @author Philipp Ossler
 */
public class DurationValueTest {

  protected final DurationValueType type = new DurationValueTypeImpl();

  @Test
  public void shouldHaveTypeNameDuration() {
    assertThat(type.getName(), is("duration"));
  }

  @Test
  public void shouldNotHaveParentType() {
    assertThat(type.getParent(), is(nullValue()));
  }

  @Test
  public void shouldBeAPrimitiveValueType() {
    assertThat(type.isPrimitiveValueType(), is(true));
  }

  @Test
  public void shouldNotBeAnAbstractType() {
    assertThat(type.isAbstract(), is(false));
  }

  @Test
  public void shouldCreateValueFromTypeWithDayTime() {
    Map<String, Object> valueInfo = Collections.<String, Object> singletonMap(DurationValueTypeImpl.VALUE_INFO_UNIT, DurationValueUnit.DayTime.name());
    DurationValue durationValue = (DurationValue) type.createValue(3600L, valueInfo);

    assertThat(durationValue.getValue(), is(3600L));
    assertThat(durationValue.getUnit(), is(DurationValueUnit.DayTime));
  }

  @Test
  public void shouldCreateValueFromTypeWithYearsAndMonths() {
    Map<String, Object> valueInfo = Collections.<String, Object> singletonMap(DurationValueTypeImpl.VALUE_INFO_UNIT, DurationValueUnit.YearsMonths.name());
    DurationValue durationValue = (DurationValue) type.createValue(13L, valueInfo);

    assertThat(durationValue.getValue(), is(13L));
    assertThat(durationValue.getUnit(), is(DurationValueUnit.YearsMonths));
  }

  @Test(expected = IllegalArgumentException.class)
  public void shouldNotCreateValueFromTypeWithoutUnit() {
    type.createValue(42L, Collections.<String, Object> emptyMap());
  }

  @Test(expected = IllegalArgumentException.class)
  public void shouldNotCreateValueFromTypeWithInvalidUnit() {
    Map<String, Object> valueInfo = Collections.<String, Object> singletonMap(DurationValueTypeImpl.VALUE_INFO_UNIT, "invalid");
    type.createValue(42L, valueInfo);
  }

  @Test
  public void shouldProvideValueInfo() {
    DurationValue value = Variables.durationValue(3600L).unit(DurationValueUnit.DayTime).create();

    Map<String, Object> valueInfo = type.getValueInfo(value);

    assertThat(valueInfo, hasEntry(DurationValueTypeImpl.VALUE_INFO_UNIT, (Object) DurationValueUnit.DayTime.name()));
  }

  @Test
  public void shouldCreateDurationWithDayTime() {
    DurationValue durationValue = Variables.durationValue(3600).unit(DurationValueUnit.DayTime).create();

    assertThat(durationValue.getValue(), is(3600L));
    assertThat(durationValue.getUnit(), is(DurationValueUnit.DayTime));
  }

  @Test
  public void shouldCreateDurationWithYearsAndMonths() {
    DurationValue durationValue = Variables.durationValue(13).unit(DurationValueUnit.YearsMonths).create();

    assertThat(durationValue.getValue(), is(13L));
    assertThat(durationValue.getUnit(), is(DurationValueUnit.YearsMonths));
  }

  @Test(expected = IllegalArgumentException.class)
  public void shouldNotCreateDurationWithoutUnit() {
    Variables.durationValue(42).create();

    fail("should not create duration without unit");
  }

}
