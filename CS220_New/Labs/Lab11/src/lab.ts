export type Coordinate = { x: number; y: number };

export interface MouseObserver {
  readonly screen: number;

  // update observer with new mouse click coordinate
  update_coordinate(coord: Coordinate): void;

  // update observer with new mouse drag event
  update_drag(start: Coordinate, end: Coordinate): void;
}

export interface Observable {
  // adds an Observer to the set of subscribers
  subscribe(subscriber: MouseObserver): void;

  // removes an Observer from the set of subscribers
  unsubscribe(subscriber: MouseObserver): void;
}

// Exercise 1

export class MouseEvents implements Observable {
  public num_screens: number;
  public subscribers: Set<MouseObserver>[]; // set of subscribers per screen

  constructor(num_screens: number) {
    this.num_screens = num_screens;
    this.subscribers = [];
    for (let i = 0; i < this.num_screens; i++) {
      this.subscribers.push(new Set<MouseObserver>());
    }
    // TODO: Implement this constructor
  }

  subscribe(subscriber: MouseObserver): void {
    // TODO: Implement this method
    if (subscriber.screen >= 0 && subscriber.screen < this.num_screens) {
      this.subscribers[subscriber.screen].add(subscriber);
    }
  }

  unsubscribe(subscriber: MouseObserver): void {
    // TODO: Implement this method
    if (subscriber.screen >= 0 && subscriber.screen < this.num_screens) {
      this.subscribers[subscriber.screen].delete(subscriber);
    }
  }

  mouse_click(screen: number, coord: Coordinate): void {
    // TODO: Implement this method
    if (screen >= 0 && screen < this.num_screens) {
      this.subscribers[screen].forEach(s => s.update_coordinate(coord));
    }
  }

  mouse_drag(screen: number, start: Coordinate, end: Coordinate): void {
    // TODO: Implement this method
    if (screen >= 0 && screen < this.num_screens) {
      this.subscribers[screen].forEach(s => s.update_drag(start, end));
    }
  }
}

// Exercise 2

// Should print out all coordinate and mouse drag updates for a specific screen
export class MouseEventLogger implements MouseObserver {
  readonly screen: number;

  constructor(screen: number) {
    this.screen = screen;
  }

  update_coordinate(coord: Coordinate): void {
    // TODO: Implement this method
    console.log(`Click: (${coord.x}, ${coord.y})`);
  }

  update_drag(start: Coordinate, end: Coordinate): void {
    // TODO: Implement this method
    console.log(`Drag: (${start.x}, ${start.y}) to (${end.x}, ${end.y})`);
  }
}

// Should print out area of bounding box around all mouse clicks, and area of bounding box for mouse drags
export class MouseEventArea implements MouseObserver {
  readonly screen: number;
  private minx: number;
  private miny: number;
  private maxx: number;
  private maxy: number;

  constructor(screen: number) {
    this.screen = screen;
    this.minx = Infinity;
    this.miny = Infinity;
    this.maxx = -Infinity;
    this.maxy = -Infinity;
    // TODO: Implement the rest of this constructor
  }

  update_coordinate(coord: Coordinate): void {
    // TODO: Implement this method
    if (coord.x < this.minx) {
      this.minx = coord.x;
    }
    if (coord.x > this.maxx) {
      this.maxx = coord.x;
    }
    if (coord.y < this.miny) {
      this.miny = coord.y;
    }
    if (coord.y > this.maxy) {
      this.maxy = coord.y;
    }
    const dx: number = Math.abs(this.maxx - this.minx);
    const dy: number = Math.abs(this.maxy - this.miny);
    console.log(`Coordinate area: ${dx * dy}`);
  }

  update_drag(start: Coordinate, end: Coordinate): void {
    // TODO: Implement this method
    const dx: number = Math.abs(end.x - start.x);
    const dy: number = Math.abs(end.y - start.y);
    console.log(`Dragged area: ${dx * dy}`);
  }
}
