package operation;

import util.filemanagers.readers.LogParser;

public class LogSequencer extends Sequencer{
	@Override
	public boolean isMoveOn() {
		String line=LogParser.lastLine(file);
		String id=String.valueOf(abilities.get(index).getLogID());
		return (line.contains(id));
	}

}
