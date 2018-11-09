import './enzyme.config.js'
import React from 'react'
import { mount } from 'enzyme'
import Port from '../src/components/Application/Port'
import Add from "../src/components/Application/Add";

const startProps1 = {
  'place': ['Change Server','Change Port'],
};
const startProps2 = {
  'message': false,
};

/* Test example using a pre-defined function */
function testInput() {
  const port = mount((
    <Port place={startProps1.place} />
  ));

  let actual = [];
  port.find('Input').map((element) => actual.push(element.prop('name')));

  expect(actual).toEqual(startProps1.place);
}

test('Check to see if table gets made correctly (Function)', testInput);

function testChange() {

  const wrapper = mount(<Port />);
  wrapper.find('Input').first().simulate('change', {target: {value: 10}});

  expect(wrapper.state('server')).toEqual(10);

}

test('Check to see if table gets made correctly (Function)', testChange);

function testChange1() {

  const wrapper = mount(<Port />);
  wrapper.find('Input').at(1).simulate('change', {target: {value: 10}});

  expect(wrapper.state('port')).toEqual(10);

}

test('Check to see if table gets made correctly (Function)', testChange1);