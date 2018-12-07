import './enzyme.config.js'
import React from 'react'
import { mount, shallow } from 'enzyme'
import Info from '../src/components/Application/Info'


const startProps = {
  'type': ["alertColor"],
  'label': ['labelpre', 'labelpre', 'labelpop'],
};

/* Test example using a pre-defined function */
function testInput() {
  const info = mount((
    <Info />
  ));

  let actual = [];
  info.find('Alert').map((element) => actual.push(element.prop('className')));
  expect(actual).toEqual(startProps.type);
}

test('Check to see if table gets made correctly (Function)', testInput);