/*
 * Copyright 2012-2014, First Three LLC
 *
 * This file is a part of Viz.
 *
 * Viz is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published
 * by the Free Software Foundation, either version 3 of the License,
 * or (at your option) any later version.
 *
 * Viz is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Viz.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.first3.viz.builders;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.first3.viz.builders.FlashPlayerResourceBuilder;
import com.first3.viz.content.ContentSource;
import com.first3.viz.download.Container;
import com.first3.viz.utils.StringBuffer;

public class YouruploadResourceBuilder extends FlashPlayerResourceBuilder {

    @Override
    public String getDownloadURL(Container container) {
        Document document = Jsoup.parse(container.toString());
        Elements playerData = document.select("div#player + script");
        if (playerData.size() > 0) {
            StringBuffer sb = StringBuffer.fromString(playerData.get(0)
                    .toString());
            String url = sb.stringBetween(",'file':				'", "'");
            return url;
        }
        return null;
    }

    @Override
    public ContentSource getContentSource() {
        return ContentSource.YOURUPLOAD;
    }
}
