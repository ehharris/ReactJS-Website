import './enzyme.config.js'
import React from 'react'
import { mount } from 'enzyme'
import Add from '../src/components/Application/Add'

const startProps1 = {
    'place': ['', '', '', ''],
};
const startProps2 = {
    'message': false,
};

/* Test example using a pre-defined function */
function testInput() {
    const add = mount((
        <Add place={startProps1.place} />
    ));

    let actual = [];
    add.find('Input').map((element) => actual.push(element.prop('value')));

    expect(actual).toEqual(startProps1.place);
}

test('Check to see if table gets made correctly (Function)', testInput);

/* Test example using a pre-defined function */
function testFade() {
    const add = mount((
        <Add place={startProps2.message} />
    ));

    let actual;
    add.find('Fade').map((element) => actual = (element.prop('in')));

    expect(actual).toEqual(startProps2.message);
}

test('Check to see if table gets made correctly (Function)', testFade);