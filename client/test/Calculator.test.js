import './enzyme.config.js'
import React from 'react'
import { mount, shallow } from 'enzyme'
import Calculator from '../src/components/Application/Calculator'


const startProps = {
  'type': ['origin', 'origin', 'destination', 'destination'],
  'label': ['labelpre', 'labelpre', 'labelpop'],
};

/* Test example using a pre-defined function */
function testInput() {
  const calculator = mount((
    <Calculator config={startProps.config} options={startProps.options}/>
  ));

  let actual = [];
  calculator.find('Input').map((element) => actual.push(element.prop('name')));
  expect(actual).toEqual(startProps.type);
}

test('Check to see if table gets made correctly (Function)', testInput);

function testLabel() {
  const calculator = mount((
    <Calculator config={startProps.config} options={startProps.options}/>
  ));

  let actual = [];
  calculator.find('Label').map((element) => actual.push(element.prop('className')));
  expect(actual).toEqual(startProps.label);
}

test('Check to see if table gets made correctly (Function)', testLabel);

function testChange() {

  // describe what we are testing
  // expect(shallow(<Calculator/>).find('Input').first().simulate())

  const wrapper = mount(<Calculator />);
  wrapper.find('Input').first().simulate('change', {target: {value: 10}});

  expect(wrapper.state('distance')).toEqual({"destination": {"latitude": 0, "longitude": 0}, "distance": 0, "origin": {"latitude": 10, "longitude": 0}, "type": "distance", "units": "miles", "version": "4"});

}

test('Check to see if table gets made correctly (Function)', testChange);