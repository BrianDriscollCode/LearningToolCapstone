import { expect } from 'vitest';
import { vi } from 'vitest';

global.fetch = vi.fn(() =>
  Promise.resolve({
    json: () => Promise.resolve({}),
  })
);



globalThis.myGlobalVar = 'test';
