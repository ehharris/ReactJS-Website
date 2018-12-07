import './enzyme.config.js'
import React from 'react'
import { mount } from 'enzyme'
import Application from '../src/components/Application/Application'

const startProps1 = {
  'place': [],
};
const startProps2 = {
  'message': false,
};

/* Test example using a pre-defined function */
function testNav() {
  const app = mount((
    <Application />
  ));

  let actual = [];
  app.find('Nav').map((element) => actual.push(element.prop('id')));

  expect(actual).toEqual(startProps1.place);
}

test('Check to see if table gets made correctly (Function)', testNav);

/* Test example using a pre-defined function */
function testNav() {
  const app = mount((
    <Application />
  ));

  let actual = [];
  app.find('Container').map((element) => actual.push(element.prop('id')));

  expect(actual).toEqual(startProps1.place);
}

test('Check to see if table gets made correctly (Function)', testNav);